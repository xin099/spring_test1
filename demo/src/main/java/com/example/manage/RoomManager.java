package com.example.manage;

import com.example.model.Room;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: spring_test1
 * @description: RoomManager
 * @author: XX
 * @create: 2022-09-08 15:03
 **/
// 房间管理器
// 也要唯一实例
@Component
public class RoomManager {
    private ConcurrentHashMap<String, Room> rooms = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, String> userIdToRoomId = new ConcurrentHashMap<>();

    public void add(Room room, int userId1, int userId2) {
        rooms.put(room.getRoomId(), room);
        userIdToRoomId.put(userId1, room.getRoomId());
        userIdToRoomId.put(userId2, room.getRoomId());
    }

    public void remove(String roomId, int userId1, int userId2) {
        rooms.remove(roomId);
        userIdToRoomId.remove(userId1);
        userIdToRoomId.remove(userId2);
    }

    public Room getRoomByRoomId(String roomId) {
        return rooms.get(roomId);
    }

    public Room getRoomByUserId(int userId) {
        String roomId = userIdToRoomId.get(userId);
        if (roomId == null) {
            // userId -> roomId 映射关系不存在, 直接返回 null
            return null;
        }
        return rooms.get(roomId);
    }
//private ConcurrentHashMap<String,Room> rooms = new ConcurrentHashMap<>();
//
//    public void insert(Room room) {
//        rooms.put(room.getRoomId(),room);
//    }
//
//    public void remove(String roomId) {
//        rooms.remove(roomId);
//    }
//
//    public Room findRoomByRoomId(String roomId) {
//        return rooms.get(roomId);
//    }


}

