package model.bo;

import model.bean.StudentBean;
import model.dao.StudentDAO;

import java.util.List;

public class StudentBO {
    private final StudentDAO studentDAO = new StudentDAO();

    public List<StudentBean> listAll() {
        return studentDAO.findAll();
    }

    public List<StudentBean> search(String keyword, String faculty) {
        String key = keyword == null ? "" : keyword.trim();
        String fac = faculty == null ? "" : faculty.trim();
        return studentDAO.search(key, fac);
    }

    public boolean add(StudentBean student) {
        return studentDAO.insert(student);
    }

    public StudentBean get(int id) {
        return studentDAO.findById(id);
    }

    public boolean update(StudentBean student) {
        return studentDAO.update(student);
    }

    public boolean delete(int id) {
        return studentDAO.delete(id);
    }
}
