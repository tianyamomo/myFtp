package cn.gavin.common.util.file;

import java.io.File;
import java.io.FileFilter;

/**
 * listFile的时候用来过滤不符合标准的文件
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class DCBFileFilter implements FileFilter{

	/****
	 * 当包含.EDI的时候就获取，
	 */
	@Override
	public boolean accept(File file) {
		String fileName = file.getName().toUpperCase();
		if(fileName.contains(".XML")){///////用.XML结尾
			///,如果当前文件的最后修改时间小于7分钟或者是大于22分钟
//			if(DateUtil.getNowTime()-file.lastModified()>7*60*1000 && DateUtil.getNowTime()-file.lastModified() < 22*60*1000){
//				return false;
//			}
			return true;
		}else{
			return false;
		}
	}

}
