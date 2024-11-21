package com.game.service;

import com.game.entity.Child;
import com.game.entity.Admin;
import com.game.repository.ChildRepository;
import com.game.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupervisorService {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private AdminRepository adminRepository;

    // Create a new Child and assign the supervisor's school
    public Child createChild(Child child, Long supervisorId) {
        Admin supervisor = adminRepository.findById(supervisorId).orElseThrow(() ->
                new IllegalArgumentException("Supervisor not found with id: " + supervisorId));

        // Assign the supervisor's school to the child
        child.setSchool(supervisor.getSchool());
        return childRepository.save(child);
    }

    @Transactional
    public void deleteChild(Long childId) {
        Child child = childRepository.findById(childId).orElseThrow(() ->
                new IllegalArgumentException("Child not found with id: " + childId));

        // Delete the child after clearing references
        childRepository.deleteById(childId);
    }

    // Get a Child by ID
    public Child getChildById(Long id) {
        return childRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Child not found with id: " + id));
    }

    // Get all Children
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    // Find Children by Name
    public List<Child> getChildrenByName(String name) {
        return childRepository.findByName(name);
    }



    // Create a new Admin
    public Admin createAdmin(Admin admin, Long supervisorId) {
        Admin supervisor = adminRepository.findById(supervisorId).orElseThrow(() ->
                new IllegalArgumentException("Supervisor not found with id: " + supervisorId));

        // Assign the supervisor's school to the admin
        admin.setSchool(supervisor.getSchool());
        return adminRepository.save(admin);
    }
    // Delete an existing Admin
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    // Get all Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findByIsSupervisorFalse();
    }

    // Get an Admin by ID
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Admin not found with id: " + id));
    }

    public List<Child> getAllChildrenInSameSchool(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() ->
                new IllegalArgumentException("Admin not found with id: " + adminId));
        // Return all children in the same school as the admin
        return childRepository.findBySchool(admin.getSchool());
    }
    public List <Admin> getAllAdminsInSameSchool(Long adminId) {
        return adminRepository.findAdminsInSameSchoolNotSupervisor(adminId);
    }
    public String getSchoolNameByAdminId(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() ->
                new IllegalArgumentException("Admin fannst ekki með ID: " + adminId));

        if (admin.getSchool() == null) {
            throw new IllegalArgumentException("Admin er ekki skráður í neinn skóla.");
        }

        return admin.getSchool().getName(); // Skila nafni skólans
    }

    public void changeAdminPassword(Long adminId, String newPassword) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Kennari fannst ekki með þetta auðkenni."));

        admin.setPassword(newPassword); // Assume passwords are stored securely (e.g., hashed)
        adminRepository.save(admin);
    }
}

