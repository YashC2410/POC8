package com.poc8.students;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@Service
public class StudentService {
@Autowired
private StudentRepository studentRepository;
private String a="Success";
private String b="Failed";
private String message="Student with ID: ";
public Page<Student> listAll(int pageNum) {
    int pageSize = 5;
    Pageable pageable = PageRequest.of(pageNum - 1, pageSize);    
    return studentRepository.findAll(pageable);
}
public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
	Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending();
	
	Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
	return studentRepository.findAll(pageable);
}
public Student createStudent(Student student) {
	return studentRepository.save(student);
}
public List<Student> getAllStudent(){
	List<Student> students=studentRepository.findAll();
	if(!students.isEmpty()) {
		return students;
	}
	else {
		return new ArrayList<Student>();
	}
}
public ResponseModule getAllStudentsTest() {
	List<Student> student=studentRepository.findAll();
	if(student.isEmpty()) {
		return new ResponseModule("No Students were Found!!",Boolean.FALSE,b);
	}
	else {
		return new ResponseModule("Number of Students Found are: "+student.size(),Boolean.TRUE,a);
	}
}
public ResponseModule getStudentByIdTest(Integer studentId) {
	Student student=studentRepository.findStudentById(studentId);
	if(student==null) {
		return new ResponseModule("No User was Found with ID: "+studentId,Boolean.FALSE,b);
	}
	else {
		return new ResponseModule(message+student.getId()+" and with Name:"+student.getFname()+" is Found",Boolean.TRUE,a);
	}
}
public Student getStudentById(Integer studentId) throws StudentNotFoundException{
	Optional<Student> student=studentRepository.findById(studentId);
	if(student.isPresent()) {
		return student.get();
	}
	else {
		throw new StudentNotFoundException(message+studentId+" was not Found");
	}
}
public ResponseModule deleteStudentByIdTest(Integer studentId) {
	Optional<Student> student=studentRepository.findById(studentId);
	if(student.isPresent()) {
		studentRepository.deleteById(studentId);
		return new ResponseModule(message+studentId+" deleted ",Boolean.TRUE,a);
	}
	else {
		return new ResponseModule("No User was Found with ID: "+studentId,Boolean.FALSE,b);
	}
}
public ResponseModule createTest(Student student) {
	Student student1=studentRepository.findStudentByEmail(student.getEmail());
	if(student1==null) {
		System.out.println(student.getPhotos());
		studentRepository.save(student);
		return new ResponseModule("Student with Email: "+student.getEmail()+" Created",Boolean.TRUE,a);
	}
	else{
		return new ResponseModule("Student with Email: "+student.getEmail()+" Already Exist",Boolean.FALSE,b);
	}
}
public ResponseModule updateStudent(Student student , Integer studentId) {
	Student student1=studentRepository.findStudentById(studentId);
	if(student1!=null) {
		student1.setFname(student.getFname());
		student1.setLname(student.getLname());
		student1.setEmail(student.getEmail());
		student1.setContact(student.getContact());
		student1.setPhotos(student.getPhotos());
		studentRepository.save(student1);
		return new ResponseModule(message+studentId+" Updated",Boolean.TRUE,a);
	}
	else {
		return new ResponseModule(message+studentId+" do not Exist",Boolean.FALSE,b);
	}
}
public Student createOrUpdateStudent(Student student) {
	if(student.getId()==null) {
	    student=studentRepository.save(student);
		return student;
	}
	else {
		Optional<Student> student1=studentRepository.findById(student.getId());
		if(student1.isPresent()) {
			Student newStudent=new Student();
			newStudent.setFname(student.getFname());
			newStudent.setLname(student.getLname());
			newStudent.setContact(student.getContact());
			newStudent.setEmail(student.getEmail());
			newStudent.setPhotos(student.getPhotos());
			newStudent=studentRepository.save(student);
			return newStudent;
		}
		else {
			student=studentRepository.save(student);
			return student;
		}
	}
	
}
public void deleteStudentById(Integer studentId) throws StudentNotFoundException{
	Optional<Student> student=studentRepository.findById(studentId);
	if(student.isPresent()) {
		studentRepository.deleteById(studentId);
	}
	else {
		throw new StudentNotFoundException(message+studentId+" was not Found");
	}
}
}
