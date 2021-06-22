package com.example.billeasy_assignment.DB;

import com.example.billeasy_assignment.Utils.Constants;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Constants.TABLE_NAME)
public class Movies{

	@PrimaryKey(autoGenerate = true)
	private int local_id;

	@ColumnInfo(name = "title")
	private String title;

	@ColumnInfo(name = "posterPath")
	private String posterPath;

	public int getLocal_id() {
		return local_id;
	}

	public void setLocal_id(int local_id) {
		this.local_id = local_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
}