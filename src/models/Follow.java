package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follows")
@NamedQueries({
    @NamedQuery(
            name = "getAllFollows",
            query = "SELECT f FROM Follow AS f ORDER BY f.id DESC"
        ),
    @NamedQuery(
            name = "getMyFollowsCount",
            query = "SELECT f FROM Follow AS f WHERE f.followEmployee = :followEmployee and f.followerEmployee = :followerEmployee"
        )
    })

@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follow_employee_id")
    private Employee followEmployee;

    @ManyToOne
    @JoinColumn(name = "follower_employee_id")
    private Employee followerEmployee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getFollowEmployee() {
        return followEmployee;
    }

    public void setFollowEmployee(Employee followEmployee) {
        this.followEmployee = followEmployee;
    }

    public Employee getFollowerEmployee() {
        return followerEmployee;
    }

    public void setFollowerEmployee(Employee followerEmployee) {
        this.followerEmployee = followerEmployee;
    }



}
