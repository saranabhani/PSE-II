package model;

import java.io.Serializable;

public class Resultid implements Serializable{

		private int student;
		private int course;
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + course;
			result = prime * result + student;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Resultid other = (Resultid) obj;
			if (course != other.course)
				return false;
			if (student != other.student)
				return false;
			return true;
		}
		
}
