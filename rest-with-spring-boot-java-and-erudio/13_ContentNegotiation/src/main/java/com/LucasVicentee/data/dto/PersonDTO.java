package com.LucasVicentee.data.dto;


//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.LucasVicentee.serializer.GenderSerializer;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

// @JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"}) // Utilizado para determinar a ordem em que os itens serão exibidos em formato JSON
//@JsonFilter("PersonFilter")
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // @JsonProperty("first_name") // Serve para alterar o nome pré-definido antes por um novo
    private String firstName;

    // @JsonProperty("last_name") // Serve para alterar o nome pré-definido antes por um novo
    @JsonInclude(JsonInclude.Include.NON_EMPTY) // Verifica a serialização para não ser renderizado caso esteja vazio
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL) // Verifica a serialização para não ser renderizado caso esteja null
    private String phoneNumber;

    private Date birthDay;

    private String address;

    // @JsonIgnore // Ignora por completo a exibição do atributo no JSON
    @JsonSerialize(using = GenderSerializer.class) // Usado para alterar o valor de um atributo referente ao que o usuário colocar Ex: "Male" = "M", "Female" = "F"
    private String gender;

    private String sensitiveData;

    public PersonDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSensitiveData() {
        return sensitiveData;
    }

    public void setSensitiveData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    @JsonFormat(pattern = "dd/MM/yyyy")
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(getId(), personDTO.getId()) && Objects.equals(getFirstName(), personDTO.getFirstName()) && Objects.equals(getLastName(), personDTO.getLastName()) && Objects.equals(getPhoneNumber(), personDTO.getPhoneNumber()) && Objects.equals(getBirthDay(), personDTO.getBirthDay()) && Objects.equals(getAddress(), personDTO.getAddress()) && Objects.equals(getGender(), personDTO.getGender()) && Objects.equals(getSensitiveData(), personDTO.getSensitiveData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getPhoneNumber(), getBirthDay(), getAddress(), getGender(), getSensitiveData());
    }
}
