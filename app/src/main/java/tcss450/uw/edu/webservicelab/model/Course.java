package tcss450.uw.edu.webservicelab.model;

import java.io.Serializable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tola on 4/22/16.
 */
public class Course implements Serializable{
    private String mCourseId;
    private String mShortDescription;
    private String mLongDescription;
    private String mPrereqs;

    public static final String ID = "id", SHORT_DESC = "shortDesc"
            , LONG_DESC = "longDesc", PRE_REQS = "prereqs";


    public Course(String courseId, String shortDescription, String longDescription, String prereqs){
        setCourseId(courseId);
        setShortDescription(shortDescription);
        setLongDescription(longDescription);
        mPrereqs = prereqs;

    }
    public void setCourseId(String courseId){
        if(courseId == null)
            throw new IllegalArgumentException("Course Id must be supplies");
        if(courseId.length() < 5)
            throw new IllegalArgumentException("Course Id must be at least five character");
        mCourseId = courseId;

    }
    public void setShortDescription(String shortDescription){
        if(shortDescription == null)
            throw new IllegalArgumentException("Course short description must be supplies");
        if(shortDescription.length() < 5)
            throw new IllegalArgumentException("Course short description must be at least five character");
        mShortDescription = shortDescription;

    }
    public void setLongDescription(String longDescription){
        if(longDescription == null)
            throw new IllegalArgumentException("Course long description must be supplies");
        if(longDescription.length() < 5)
            throw new IllegalArgumentException("Course long description must be at least five character");
        mLongDescription = longDescription;

    }
    public void setPrereqs(String prereqs){
        mPrereqs = prereqs;
    }
    public String getCourseId(){
        return mCourseId;
    }
    public String getPrereqs(){
        return mPrereqs;
    }
    public String getShortDescription(){
        return mShortDescription;
    }
    public String getLongDescription(){
        return mLongDescription;
    }

    @Override
    public String toString(){
        return "edu.UW.sdendaa.webServiceLab.model.course{" +
                ", mCourseId = " + mCourseId + '\'' +
                ", mShortDescription = " + mShortDescription + '\'' +
                ", mLongDescription = " + mLongDescription + '\'' +
                ", mPrereqs = " + mPrereqs + '}';
    }
    /**
     * Parses the json string, returns an error message if unsuccessful.
     * Returns course list if success.
     * @param courseJSON
     * @return reason or null if successful. 
     */
    public static String parseCourseJSON(String courseJSON, List<Course> courseList) {
        String reason = null;
        if (courseJSON != null) {
            try {
                JSONArray arr = new JSONArray(courseJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    Course course = new Course(obj.getString(Course.ID), obj.getString(Course.SHORT_DESC)
                            , obj.getString(Course.LONG_DESC), obj.getString(Course.PRE_REQS));
                    courseList.add(course);
                }
            } catch (JSONException e) {
                reason =  "Unable to parse data, Reason: " + e.getMessage();
            }

        }
        return reason;
    }


}
