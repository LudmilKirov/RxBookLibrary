//package com.example.rxbooklibrary.persistence;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import com.example.rxbooklibrary.models.VolumeInfo;
//
//import java.util.List;
//
//@Dao
//public interface BookDao {
//    @Insert
//    long[] insertVolumeInfor(VolumeInfo... volumeInfos);
//
//    @Query("SELECT * FROM library")
//    LiveData<List<VolumeInfo>> getNotes();
//
//    @Query("SELECT * FROM library WHERE title LIKE :title")
//    List<VolumeInfo> getNoteWithCustomQuery(String title);+
//
//
//    @Delete
//    int delete(VolumeInfo... volumeInfos);
//
//    @Update
//    int update(VolumeInfo volumeInfo);
//}
