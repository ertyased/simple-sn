package ru.itmo.wp.form;

import ru.itmo.wp.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostInformation {

    @NotBlank
    @NotEmpty
    @Size(min = 5, max = 100)
    String title;
    @NotBlank
    @NotEmpty
    @Size(min = 5, max = 10000)
    String text;

    Long userId;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
