package org.upgrad.models;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5938933733749572013L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    // this annotation completes the many-to-many declaration created
    // in the Question class
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categoryList")
    private List<Question> questionList;

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Category() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
