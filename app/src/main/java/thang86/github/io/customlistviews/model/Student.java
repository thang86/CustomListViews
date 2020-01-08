package thang86.github.io.customlistviews.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author ThangTX2
 */
public class Student implements Parcelable, Cloneable {
    private int imgAvatar;
    private String nameStudent;
    private String className;
    private String birthday;

    public Student(int imgAvatar, String nameStudent, String className, String birthday) {
        this.imgAvatar = imgAvatar;
        this.nameStudent = nameStudent;
        this.className = className;
        this.birthday = birthday;
    }

    public Student() {
    }

    protected Student(Parcel in) {
        imgAvatar = in.readInt();
        nameStudent = in.readString();
        className = in.readString();
        birthday = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(int imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgAvatar);
        dest.writeString(nameStudent);
        dest.writeString(className);
        dest.writeString(birthday);
    }

    public int compareTo(Student student) {
        Student compare = (Student) student;

        if (compare.getImgAvatar() == this.getImgAvatar() && compare.getNameStudent() == this.getNameStudent()
                && compare.getClassName() == this.getClassName() && compare.getBirthday() == this.getBirthday()) {
            return 0;
        }
        return 1;
    }

    @Override
    public Student clone() {

        Student clone;
        try {
            clone = (Student) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        return clone;
    }
}
