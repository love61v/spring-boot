package cn.yesway.mapshifting.model;

public class Area {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area.id
     *
     * @mbggenerated Sat Mar 18 17:13:36 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area.name
     *
     * @mbggenerated Sat Mar 18 17:13:36 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area.parentid
     *
     * @mbggenerated Sat Mar 18 17:13:36 CST 2017
     */
    private Integer parentid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area.id
     *
     * @return the value of area.id
     *
     * @mbggenerated Sat Mar 18 17:13:36 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area.id
     *
     * @param id the value for area.id
     *
     * @mbggenerated Sat Mar 18 17:13:36 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area.name
     *
     * @return the value of area.name
     *
     * @mbggenerated Sat Mar 18 17:13:36 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area.name
     *
     * @param name the value for area.name
     *
     * @mbggenerated Sat Mar 18 17:13:36 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area.parentid
     *
     * @return the value of area.parentid
     *
     * @mbggenerated Sat Mar 18 17:13:36 CST 2017
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area.parentid
     *
     * @param parentid the value for area.parentid
     *
     * @mbggenerated Sat Mar 18 17:13:36 CST 2017
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}