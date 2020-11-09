package seedu.duke.global.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

//    "moduleCode",
//    "title",
//    "moduleCredit",
//    "department",
//    "faculty",
//    "semester"
//    "preclusion",
//    "attibutes" = [
//     su: false, Index 0 of attribute
//     sfs: false, Index 1 of attribute
//     ssgf: false, Index 2 of attribute
//     ism: false, Index 3 of attribute
//     fyp: false, Index 4 of attribute
//     year: false, Index 5 of attribute
//     grsu: false, Index 6 of attribute
//     lab: false, Index 7 of attribute
//     urop: false, Index 8 of attribute
//    ],
//    "prerequisite",
//    "corequisite"

//@@author Khenus
/**
 * A class created to store the full data of each mod after JSON conversion.
 */
public class FullModule {
    private String moduleCode;
    private String title;
    private Integer moduleCredit;
    private String department;
    private String faculty;
    private List<Integer> semester = new ArrayList<Integer>();
    private String preclusion;
    private List<Boolean> attributes = new ArrayList<Boolean>();
    private String prerequisite;
    private String corequisite;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    public Integer getModuleCredit() {
        return moduleCredit;
    }

    public String getDepartment() {
        return department;
    }

    public String getFaculty() {
        return faculty;
    }

    public List<Integer> getSemester() {
        return semester;
    }

    public String getPreclusion() {
        return preclusion;
    }

    public List<Boolean> getAttributes() {
        return attributes;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public String getCorequisite() {
        return corequisite;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FullModule) == false) {
            return false;
        }
        FullModule rhs = ((FullModule) other);
        return new EqualsBuilder().append(moduleCode, rhs.moduleCode).append(title, rhs.title)
                .append(moduleCredit, rhs.moduleCredit).append(department, rhs.department)
                .append(faculty, rhs.faculty).append(semester, rhs.semester).append(preclusion, rhs.preclusion)
                .append(attributes, rhs.attributes).append(prerequisite, rhs.prerequisite)
                .append(corequisite, rhs.corequisite).append(additionalProperties, rhs.additionalProperties).isEquals();
    }
}
