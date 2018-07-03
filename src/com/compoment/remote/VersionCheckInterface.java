package com.compoment.remote;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.compoment.remote.IphoneViewControllerXibInterface;

public interface VersionCheckInterface  extends Remote {

	public String hasNewVersion(String currentVersion) throws RemoteException;
	public boolean blackList(String macip) throws RemoteException;

}
