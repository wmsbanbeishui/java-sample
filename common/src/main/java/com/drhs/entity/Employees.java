package com.drhs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * employees table. Contains 107 rows. References with departments,
jobs, job_history tables. Contains a self reference.
 * @TableName EMPLOYEES
 */
@TableName(value ="EMPLOYEES")
@Data
public class Employees implements Serializable {
    /**
     * Primary key of employees table.
     */
    @TableId
    private Integer employeeId;

    /**
     * First name of the employee. A not null column.
     */
    private String firstName;

    /**
     * Last name of the employee. A not null column.
     */
    private String lastName;

    /**
     * Email id of the employee
     */
    private String email;

    /**
     * Phone number of the employee; includes country code and area code
     */
    private String phoneNumber;

    /**
     * Date when the employee started on this job. A not null column.
     */
    private Date hireDate;

    /**
     * Current job of the employee; foreign key to job_id column of the
jobs table. A not null column.
     */
    private String jobId;

    /**
     * Monthly salary of the employee. Must be greater
than zero (enforced by constraint emp_salary_min)
     */
    private BigDecimal salary;

    /**
     * Commission percentage of the employee; Only employees in sales
department elgible for commission percentage
     */
    private BigDecimal commissionPct;

    /**
     * Manager id of the employee; has same domain as manager_id in
departments table. Foreign key to employee_id column of employees table.
(useful for reflexive joins and CONNECT BY query)
     */
    private Integer managerId;

    /**
     * Department id where employee works; foreign key to department_id
column of the departments table
     */
    private Integer departmentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Employees other = (Employees) that;
        return (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getFirstName() == null ? other.getFirstName() == null : this.getFirstName().equals(other.getFirstName()))
            && (this.getLastName() == null ? other.getLastName() == null : this.getLastName().equals(other.getLastName()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getHireDate() == null ? other.getHireDate() == null : this.getHireDate().equals(other.getHireDate()))
            && (this.getJobId() == null ? other.getJobId() == null : this.getJobId().equals(other.getJobId()))
            && (this.getSalary() == null ? other.getSalary() == null : this.getSalary().equals(other.getSalary()))
            && (this.getCommissionPct() == null ? other.getCommissionPct() == null : this.getCommissionPct().equals(other.getCommissionPct()))
            && (this.getManagerId() == null ? other.getManagerId() == null : this.getManagerId().equals(other.getManagerId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
        result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getHireDate() == null) ? 0 : getHireDate().hashCode());
        result = prime * result + ((getJobId() == null) ? 0 : getJobId().hashCode());
        result = prime * result + ((getSalary() == null) ? 0 : getSalary().hashCode());
        result = prime * result + ((getCommissionPct() == null) ? 0 : getCommissionPct().hashCode());
        result = prime * result + ((getManagerId() == null) ? 0 : getManagerId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", employeeId=").append(employeeId);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", hireDate=").append(hireDate);
        sb.append(", jobId=").append(jobId);
        sb.append(", salary=").append(salary);
        sb.append(", commissionPct=").append(commissionPct);
        sb.append(", managerId=").append(managerId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}