package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Notes;
import com.example.demo.entity.User;

@Service
public interface NotesService {
	
	public Notes saveNotes(Notes notes);
	
	public Notes getNotesById(int id);
	
	//public List<Notes> getNotesByUser(User user);
	
	public Page<Notes> getNotesByUser(User user, int pageNo);
	
	public Notes updateNotes(Notes notes);
	
	public boolean deleteNotes(int id);
}