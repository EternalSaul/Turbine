package nchu.turbine.interfaces.dao;

import java.io.IOException;
import java.util.Vector;

import nchu.turbine.view.CompletedTaskPanel;

public interface ICompletedTaskDao {
	Vector<CompletedTaskPanel> find();
	void save(Vector<CompletedTaskPanel> ts);
}
