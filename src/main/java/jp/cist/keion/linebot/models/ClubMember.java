package jp.cist.keion.linebot.models;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="club_member")
public class ClubMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //学籍番号
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studentNumber;

    //LINEのID
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String lineid;

    //名前
    @Column
    private String name;

    @Column(nullable = false)
    private LocalDateTime updated_at;

    public ClubMember() {
    }

    public ClubMember(String lineid) {
        this.lineid = lineid;
        this.updated_at = LocalDateTime.now();
    }

    public ClubMember(String lineid, String studentNumber, String name){
        this.lineid = lineid;
        this.studentNumber = studentNumber;
        this.name = name;
        this.updated_at = LocalDateTime.now();
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getLineid() {
        return lineid;
    }

    public String getName() {
        return name;
    }

}
