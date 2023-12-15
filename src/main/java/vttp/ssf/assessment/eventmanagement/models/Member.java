package vttp.ssf.assessment.eventmanagement.models;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Member {
    
    @NotBlank(message = "This is a required field")
    @Size(min = 5, max = 25, message = "Name should be between 5 and 25 characters")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of birth must be in the past")
    private Date dob;

    @NotBlank(message = "This is a required field")
    @Email(message = "Invalid email format")
    @Size(max = 50, message = "Max characters is 50")
    private String email;

    @NotBlank(message = "This is a required field")
    @Pattern(regexp = "[0-9]{7,}", message = "Invalid phone number format")
    private String phoneNumber;

    @Min(value = 1, message = "At least 1 ticket is required")
    @Max(value = 3, message = "Ticket limit is 3")
    private Integer ticket;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    
}
