package myclass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Animal
{
   private StringProperty gender;
   private StringProperty breed;
   private StringProperty type;
   private IntegerProperty age;
	private int offset=(50 + (25 * 2) + (25 * 2) + (3 * 4) + (3 + 4) + (25 * 2) + (25 * 2) + (25 * 2) + (1 * 4));
   
	public Animal() 
	{
		gender=new SimpleStringProperty("");
		breed=new SimpleStringProperty("");
		type=new SimpleStringProperty("");
		age=new SimpleIntegerProperty(0);
	}
	public Animal(String gender, String breed,String type,int age)
	{
		this.gender = new SimpleStringProperty(gender);
		this.breed = new SimpleStringProperty(breed);;
		this.type=new SimpleStringProperty(type);
		this.age =new SimpleIntegerProperty(age);
	}
	public Animal(Animal animal)
	{
		this.gender=animal.gender;
		this.breed=animal.breed;
		this.type=animal.type;
		this.age=animal.age;
	}
	public String getGender() {
		return gender.get();
	}
	public void setGender(String gender) {
		this.gender.set(gender);
	}
	public String getBreed() {
		return breed.get();
	}

	public StringProperty genderProperty() {
		return gender;
	}

	public StringProperty breedProperty() {
		return breed;
	}

	public StringProperty typeProperty() {
		return type;
	}

	public IntegerProperty ageProperty() {
		return age;
	}

	public void setBreed(String breed) {
		this.breed.set(breed);
	}
	public String getType() {
		return type.get();
	}
	public void setType(String type) {
		this.type.set(type);
	}
	public int getAge() {
		return age.get();
	}
	public void setAge(int age) {
		this.age.set(age);
	}
	public void display()
	{
	   	System.out.println("the gender is: "+gender);
	   	System.out.println("the breed is: "+breed);
		System.out.println("the type is: "+type);
	   	System.out.println("the age is: "+age);
	}



	public void initialize() {
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(new File("visit.dat"), "rw");
			for (int idx = 1; idx <= 1000; idx++) {
				f.seek((idx - 1000) * offset);
				f.writeInt(0);
				f.writeUTF("");
				f.writeUTF("");
				f.writeUTF("");
				f.writeUTF("");
				f.writeInt(0);
				f.writeInt(0);
				f.writeInt(0);
				f.writeUTF("");
				f.writeUTF("");
				f.writeInt(0);
				f.writeInt(0);
				f.writeInt(0);
				f.writeInt(0);
				f.writeInt(0);
				f.writeInt(0);
				f.writeUTF("");
				f.writeUTF("");
				f.writeUTF("");
				f.writeInt(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (f != null) {
					f.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public Animal retrieve(int id) {
		RandomAccessFile file = null;

		try {
			file = new RandomAccessFile("visit.dat", "rw");
			file.seek((id - 1) * offset);
			setBreed(file.readUTF());
			setType(file.readUTF());
			setGender(file.readUTF());
			setAge(file.readInt());

			return this;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
