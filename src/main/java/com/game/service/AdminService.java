package com.game.service;

import com.game.entity.Admin;
import com.game.entity.Child;
import com.game.errors.AdminNotFoundException;
import com.game.repository.AdminRepository;
import com.game.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ChildRepository childRepository;

    /**
     * Retrieve all children in the database.
     *
     * @return List of all children.
     */
    public List<Child> getAllChildren() {
        List<Child> children = childRepository.findAll();
        System.out.println("All Children: " + children); // Debug log
        return children;
    }

    /**
     * Retrieve all children managed by a specific admin.
     *
     * @param adminId The ID of the admin.
     * @return List of children managed by the admin.
     */
    public List<Child> getChildrenManagedByAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() ->
                new IllegalArgumentException("Admin not found with id: " + adminId));

        List<Child> managedChildren = admin.getChildren();
        System.out.println("Managed Children for Admin " + adminId + ": " + managedChildren); // Debug log

        return managedChildren;
    }

    /**
     * Add a child to the admin's managed group.
     *
     * @param adminId The ID of the admin.
     * @param childId The ID of the child to add.
     */
    @Transactional
    public void addChildToAdmin(Long adminId, Long childId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() ->
                new IllegalArgumentException("Admin not found with id: " + adminId));

        Child child = childRepository.findById(childId).orElseThrow(() ->
                new IllegalArgumentException("Child not found with id: " + childId));

        if (!admin.getChildren().contains(child)) {
            admin.getChildren().add(child); // Add child to admin
        } else {
            throw new IllegalArgumentException("Child is already managed by this admin.");
        }

        adminRepository.save(admin); // Persist changes
    }

    /**
     * Remove all children from the admin's managed group.
     *
     * @param adminId The ID of the admin.
     */
    @Transactional
    public void clearChildrenFromAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() ->
                new IllegalArgumentException("Admin not found with id: " + adminId));
        if (admin.isSupervisor()) {
            throw new IllegalArgumentException("Supervisors cannot use AdminService.");
        }
        admin.getChildren().clear();
        adminRepository.save(admin);
        System.out.println("All children removed from Admin " + adminId); // Debug log
    }

    /**
     * Remove a specific child from the admin's managed group.
     *
     * @param adminId The ID of the admin.
     * @param childId The ID of the child to remove.
     */
    @Transactional
    public void removeChildFromAdmin(Long adminId, Long childId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() ->
                new IllegalArgumentException("Admin not found with id: " + adminId));

        Child child = childRepository.findById(childId).orElseThrow(() ->
                new IllegalArgumentException("Child not found with id: " + childId));

        // Remove the relationship
        admin.removeChildFromAdmin(child);
        adminRepository.save(admin); // Persist changes
    }

	public Child getChildById(Long childId){
        Child child = childRepository.findById(childId).orElseThrow(() ->
                new IllegalArgumentException("child fannst ekki með id: " + childId));
		return child;
	}

	public boolean childExistById(Long childId){
		return childRepository.existsById(childId);
	}

    public String getUsername(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() ->
                new AdminNotFoundException("Admin not found with id: " + adminId));
        System.out.println("Username: " + admin.getUsername());
        return admin.getUsername();
    }
    public List<Child> getUnmanagedChildren(Long adminId) {
        List<Child> unmanagedChildren = childRepository.findUnmanagedChildrenByAdminIdAndSchoolId(adminId);
        return unmanagedChildren != null ? unmanagedChildren : Collections.emptyList();
    }

    public String getSchoolNameByAdminId(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() ->
                new IllegalArgumentException("Admin fannst ekki með id: " + adminId));

        if (admin.getSchool() == null) {
            throw new IllegalArgumentException("Admin er ekki skráður í neinn skóla.");
        }

        return admin.getSchool().getName(); // Assuming `School` has a `getName()` method
    }


}
