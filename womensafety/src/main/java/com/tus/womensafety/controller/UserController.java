package com.tus.womensafety.controller;

import com.tus.womensafety.dto.*;
import com.tus.womensafety.entity.UserEntity;
import com.tus.womensafety.exceptions.CouldNotRetrieveUserException;
import com.tus.womensafety.exceptions.ResourceNotFoundException;
import com.tus.womensafety.repository.RelativeRepository;
import com.tus.womensafety.repository.UserRepository;
import com.tus.womensafety.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@Transactional
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RelativeRepository relativeRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{id}/relatives", method = RequestMethod.GET)
    public ResponseEntity<?> getRelativesById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            userService.getUserById(id);
            List<UserResponseDTO> userEntityArrayList = userService.getRelativesById(id);
            Object o = userEntityArrayList;
            Map<String,ArrayList<String>> list = new HashMap<>();

            ArrayList<String> deleteList = new ArrayList<>();
            userEntityArrayList.stream().forEach(i->{
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(((UserResponseDTO)i).getId())
                        .toUri();
                deleteList.add(location.toString());
            });
            list.put("DELETE",deleteList);

            MyObject myObject = new MyObject(o,list);
            return new ResponseEntity<>(myObject, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            throw new ResourceNotFoundException("could not Get Relatives for User", true);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws CouldNotRetrieveUserException, ResourceNotFoundException {
        try {

            UserResponseDTO userResponseDTO = userService.getUserById(id);
            Object o = userResponseDTO;
            Map<String,ArrayList<String>> list = new HashMap<>();
            ArrayList<String> deleteList = new ArrayList<>();
            ArrayList<String> updateList = new ArrayList<>();

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri();
            deleteList.add(location.toString());
            updateList.add(location.toString());

            userResponseDTO.getRelatives().stream().forEach(i->{
                URI location2 = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/relative/{id}")
                        .buildAndExpand(i.getId())
                        .toUri();
                deleteList.add(location2.toString());
            });

            location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/relative/{id}")
                    .buildAndExpand("other_user_id")
                    .toUri();
            updateList.add(location.toString());

            list.put("DELETE",deleteList);
            list.put("PUT", updateList);

            MyObject myObject = new MyObject(o,list);


            return new ResponseEntity<>(myObject, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            throw new ResourceNotFoundException("could not get User", true);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<UserEntity>> getAllUsers() throws ResourceNotFoundException {
        try {
            return new ResponseEntity<List<UserEntity>>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("could not get Users", true);
        }
    }

    @RequestMapping(value = "/user/relatives", method = RequestMethod.GET)
    public ResponseEntity<List<UserResponseDTO>> getAllUsersWithRelatives() throws ResourceNotFoundException {
        try {
            return new ResponseEntity<List<UserResponseDTO>>(userService.getAllUsersWithRelatives(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("could not get User relatives", true);
        }
    }

//    @RequestMapping(value = "/user/{id}/relatedto", method = RequestMethod.GET)
//    public ResponseEntity<List<UserResponseDTO>> getAllUsersWhoHasCurrentUserAsRelative(@PathVariable Long id) throws ResourceNotFoundException {
//        try {
//            return new ResponseEntity<List<UserResponseDTO>>(userService.getAllUsersWhoHasCurrentUserAsRelative(id), HttpStatus.OK);
//        } catch (Exception e) {
//            throw new ResourceNotFoundException("could not get User relatives", true);
//        }
//    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@Valid @RequestBody UserPostDTO userPostDTO) throws ResourceNotFoundException {
        try {

            UserResponseDTO savedUser = userService.addUser(userPostDTO);

            Object o = savedUser;
            Map<String,ArrayList<String>> list = new HashMap<>();
            ArrayList<String> deleteList = new ArrayList<>();
            ArrayList<String> updateList = new ArrayList<>();
            ArrayList<String> getList = new ArrayList<>();

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();
            deleteList.add(location.toString());
            updateList.add(location.toString());
            getList.add(location.toString());

            savedUser.getRelatives().stream().forEach(i->{
                URI location2 = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}/relative/{id}")
                        .buildAndExpand(savedUser.getId(),i.getId())
                        .toUri();
                deleteList.add(location2.toString());
            });

            location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}/relative/other_user_id")
                    .buildAndExpand(savedUser.getId())
                    .toUri();
            updateList.add(location.toString());

            list.put("DELETE",deleteList);
            list.put("PUT", updateList);
            list.put("GET", getList);

            MyObject myObject = new MyObject(o,list);
//            URI location = ServletUriComponentsBuilder
//                    .fromCurrentRequest().path("/{id}")
//                    .buildAndExpand(savedUser.getId())
//                    .toUri();
//            return ResponseEntity.created(location).build();



            return new ResponseEntity<>(myObject, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResourceNotFoundException("could not add User", true);
        }

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessageDTO> deleteUser(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<ResponseMessageDTO>(new ResponseMessageDTO("Successfully deleted user " + id), HttpStatus.OK);
        }catch (Exception e){
            throw new ResourceNotFoundException("could not delete User", true);
        }
    }

    @RequestMapping(value = "/user/{userid}/relative/{relativeid}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessageDTO> deleteUserRelative(@PathVariable Long userid, @PathVariable Long relativeid) throws ResourceNotFoundException {
        try {
            userService.getUserById(userid);
            userService.deleteUserRelative(userid, relativeid);
            return new ResponseEntity<ResponseMessageDTO>(new ResponseMessageDTO("Successfully deleted relative" + relativeid + "from user "), HttpStatus.OK);
        }catch (Exception e){
            throw new ResourceNotFoundException("could not delete relative", true);
        }
    }


    @RequestMapping(value = "/user/{userid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserDetailsById(@PathVariable Long userid, @Valid @RequestBody UserDTO userDTO) throws ResourceNotFoundException {
        try {
            UserResponseDTO savedUser = userService.updateUserDetailsById(userid, userDTO);

            Object o = savedUser;
            Map<String,ArrayList<String>> list = new HashMap<>();
            ArrayList<String> deleteList = new ArrayList<>();
            ArrayList<String> updateList = new ArrayList<>();
            ArrayList<String> getList = new ArrayList<>();

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri();
            deleteList.add(location.toString());
            updateList.add(location.toString());
            getList.add(location.toString());

            savedUser.getRelatives().stream().forEach(i->{
                URI location2 = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/relative/{id}")
                        .buildAndExpand(i.getId())
                        .toUri();
                deleteList.add(location2.toString());
            });

            location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/relative/other_user_id")
                    .buildAndExpand(savedUser.getId())
                    .toUri();
            updateList.add(location.toString());

            list.put("DELETE",deleteList);
            list.put("PUT", updateList);
            list.put("GET", getList);

            MyObject myObject = new MyObject(o,list);

            return new ResponseEntity<>(myObject, HttpStatus.OK);
        }catch (Exception e){
            throw new ResourceNotFoundException("could not update User", true);
        }
    }

    @RequestMapping(value = "/user/{userid}/relative/{relativeid}", method = RequestMethod.PUT)
    public ResponseEntity<?> addUserRelative(@PathVariable Long userid, @PathVariable Long relativeid) throws ResourceNotFoundException {
        try {
            UserResponseDTO updatedEntity = userService.addUserRelative(userid, relativeid);

            Object o = updatedEntity;
            Map<String,ArrayList<String>> list = new HashMap<>();
            ArrayList<String> deleteList = new ArrayList<>();

                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .buildAndExpand()
                        .toUri();
                deleteList.add(location.toString());

            list.put("DELETE",deleteList);

            MyObject myObject = new MyObject(o,list);

            return new ResponseEntity<>(myObject, HttpStatus.OK);
        }catch (Exception e){
            throw new ResourceNotFoundException("could not add relative", true);
        }
    }

}
