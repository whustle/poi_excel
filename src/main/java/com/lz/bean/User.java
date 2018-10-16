package com.lz.bean;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable{

	private static final long serialVersionUID = -760261747951003295L;
	private Integer id;
	private String name;
	private Integer age;
}
