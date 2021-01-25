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

@Table(name = "goods")
@NamedQueries({
	@NamedQuery(
            name = "getMyGood",
            query = "SELECT g FROM Good AS g WHERE g.goodEmployee = :goodEmployee and g.goodReportId = :goodReportId"
        )
})

@Entity
public class Good {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "good_employee")
    private Employee goodEmployee;

    @Column(name = "good_report_id")
    private Integer goodReportId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getGoodEmployee() {
		return goodEmployee;
	}

	public void setGoodEmployee(Employee goodEmployee) {
		this.goodEmployee = goodEmployee;
	}

	public Integer getGoodReportId() {
		return goodReportId;
	}

	public void setGoodReportId(Integer goodReportId) {
		this.goodReportId = goodReportId;
	}
}
