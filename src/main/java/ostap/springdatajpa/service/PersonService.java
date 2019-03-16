package ostap.springdatajpa.service;

import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ostap.springdatajpa.dto.MultiPartFileCustom;
import ostap.springdatajpa.dto.request.PersonRequest;
import ostap.springdatajpa.dto.response.DataResponse;
import ostap.springdatajpa.dto.response.PersonResponse;
import ostap.springdatajpa.entity.Person;
import ostap.springdatajpa.repository.PersonRepository;
import ostap.springdatajpa.specification.PersonSpecification;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public void save(PersonRequest personRequest) throws IOException {
        String img = personRequest.getPhoto();
        String[] contentAndMeta = img.split(",");
        String[] meta = contentAndMeta[0].split("/");
        String expansion = meta[1].split(";")[0];
        String name = savePerson(personRequest, expansion);
        BASE64Decoder base64 = new BASE64Decoder();
        byte[] content = base64.decodeBuffer(contentAndMeta[1]);
        MultiPartFileCustom multiPart = new MultiPartFileCustom(content, name, expansion);
        saveFile(multiPart);
    }


    private String savePerson(PersonRequest personRequest, String expansion) {
        Person person = new Person();
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        person.setAge(personRequest.getAge());
        person = personRepository.saveAndFlush(person);
        person.setPathToPhoto("\\images\\" + person.getId() + "." + expansion);
        person = personRepository.save(person);
        return String.valueOf(person.getId());
    }

    private void saveFile(MultiPartFileCustom fileCustom) throws IOException {
//        File pathFile = new File("C:\\Users\\user\\Desktop\\myPictures");//for Windows
        File pathFile = new File("src/main/resources/static/images");//for mac or linux
        if (!pathFile.exists()) {
            pathFile.mkdirs(); // blalala
        }
        //        File file = new File(pathFile+"\\"+fileCustom.getOriginalFilename()); //for windows
        File file = new File(pathFile + "/" + fileCustom.getOriginalFilename()); //for mac
        fileCustom.transferTo(file);
        file.createNewFile();
    }


    public DataResponse<PersonResponse> findAll(String value, Integer page, Integer size, String fieldName, Sort.Direction direction) {
        Sort sort = Sort.by(direction, fieldName);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Person> pagePerson;
        if (value != null && !value.equals("")) {
            PersonSpecification specification = new PersonSpecification(value);
            pagePerson = personRepository.findAll(specification, pageRequest);
        } else {
            pagePerson = personRepository.findAll(pageRequest);
        }
        return new DataResponse<PersonResponse>(pagePerson.stream().map(PersonResponse::new).collect(Collectors.toList()), pagePerson);
    }

    @Transactional
    public PersonResponse findOne(Integer id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            return new PersonResponse(personOptional.get());
        } else {
            throw new IllegalArgumentException("Person with id " + id + " not found");
        }
    }

    public void delete(Integer id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();


            String folderAndFileName = person.getPathToPhoto().replace("\\", "/");
            String url = "src/main/resources/static" + folderAndFileName;


            File photo = new File(url);
            if (photo.delete()) {
                personRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("Can not delete photo");
            }
        } else {
            throw new IllegalArgumentException("Person with id " + id + " not found");
        }
    }
}
