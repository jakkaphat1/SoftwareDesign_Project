package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service  // เพิ่ม @Service เพื่อให้ Spring Boot รู้จักคลาสนี้
public class UserServiceImpl implements UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
    @Autowired  // ใช้ @Autowired เพื่อ inject UserRepository
    private UserRepository userRepository;

//    @Override
//    public User save(UserDto userDto) {
//        // สร้าง object User จากข้อมูลใน UserDto
//        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole(), userDto.getFullname());
//        
//        // บันทึกผู้ใช้ลงในฐานข้อมูลผ่าน UserRepository
//        return userRepository.save(user);
//    }
    
//    @Override
//    public User save(UserDto userDto) {
//        // กำหนดค่า role เป็น "USER" โดยอัตโนมัติ
//        String role = "USER";
//        
//        // สร้าง object User จากข้อมูลใน UserDto โดยใช้ role ที่กำหนดไว้
//        User user = new User(userDto.getEmail(), 
//                             passwordEncoder.encode(userDto.getPassword()), 
//                             role, 
//                             userDto.getFullname());
//        
//        // บันทึกผู้ใช้ลงในฐานข้อมูลผ่าน UserRepository
//        return userRepository.save(user);
//    }
    
    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getEmail(), 
                             passwordEncoder.encode(userDto.getPassword()), 
                             userDto.getRole(), 
                             userDto.getFullname());
        return userRepository.save(user);
    }
    
}
