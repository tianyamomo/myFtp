package cn.gavin.common.util.file;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.druid.util.StringUtils;

import cn.gavin.common.util.date.DateUtil;

/****
 * 对本地文件的本地操作
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class FileAction {
	
	private static Logger logger = Logger.getLogger(FileAction.class);
	
	
	/****
	 *  移动文件
	 * @param file ： 源文件
	 * @param destination ：目标地址
	 */
	public static void move(File file,String destination){
		if(file.isFile() && file.canExecute()){
			try{
				boolean flag = file.renameTo(new File(destination+file.getName()));
				System.out.println(flag);
			}catch(Exception e){
				logger.error("move file failed",e);
			}
		}
	}
	
	/***
	 * 将ls中的文件移动到Destination路径下
	 * @param ls 源文件List 
	 * @param destination 目标地址 
	 * @param moveCount 移动的文件个数
	 */
	public static void move(List<File> ls,String destination,int moveCount){
		String directory = null;
		for(int i = 0; i < moveCount; i++){
			if(null != ls.get(i)){
				File src = ls.get(i);
				if(i == 0){
					directory = src.getParent();
				}
				if(src.isFile()){
					boolean flag = src.renameTo(new File(destination+src.getName()));
					if(!flag){
						logger.warn("move file failed");
					}
				}
			}
		}
	}
	
	/***
	 * 判断是否在sourceDir文件夹中有超过一定时间段(设定为15分钟)的文件，如果有，则将文件移到destDir文件夹
	 * @param sourceDir 源文件夹
	 * @param destDir 目标文件夹
	 * @param duration 时长，以分钟作单位
	 */
	public static void moveToBackup(String sourceDir,String destDir,long duration){
		if((!StringUtils.isEmpty(sourceDir)) && (!StringUtils.isEmpty(destDir))){
			logger.info("sourceDir :" + sourceDir + "destDir" + destDir);
			File file = new File(sourceDir);
			if(file.exists() && file.isDirectory()){
				File[] fileLs = file.listFiles();
				long nowTime = DateUtil.getNowTime();
				if(fileLs.length>0){
					for(File f : fileLs){
						if(nowTime-f.lastModified()>duration*60*1000  && f.canWrite()){
							f.renameTo(new File(destDir+f.getName()));
						}
					}
				}
			}
		}
	}
	
	
	/****
	 * 将超过了duration时间的direction路径下的文件删除
	 * @param direction backup files directory
	 * @param duration overtime
	 */
	public static void deleteFiles(String direction,Long duration){
		File file = new File(direction);
		if(file.isDirectory()){
			File[] ls = file.listFiles();
			for(int i = 0; i < ls.length; i++){
				File f = ls[i];
				if((new Date().getTime() - f.lastModified())>duration){
					f.delete();
				}
			}
		}else{
			logger.warn("direction is not real direction");
		}
	}

	
//	public static void main(String[] args) {
//		move(new File("E:/DBCWorkspace/CodecoXmlParse/files/1451870220073CMA_CODECO.XML"),"E:/DBCWorkspace/CodecoXmlParse/files/childfiles/");
//	}
}
