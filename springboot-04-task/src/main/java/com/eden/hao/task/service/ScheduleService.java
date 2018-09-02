package com.eden.hao.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
	
	/**
	 *  cron 表达式: second(秒)、minute(分), hour(时), day of month(日), month(月),and day of week(周)
	 *  
	 *  例如: * * * * * MON-FRI  ---> 表示周一到周五, 每一秒中都运行一次
	 *  
	 *  	  0 * * * * MON-SAT  ---> 表示周一到周六, 只有到末尾是 0 秒的时候运行一次(每隔10秒运行一次).
	 *  
	 */
	 /**
     * second(秒), minute（分）, hour（时）, day of month（日）, month（月）, day of week（周几）.
     * 0 * * * * MON-FRI
     *  【0 0/5 14,18 * * ?】 每天14点整，和18点整，每隔5分钟执行一次
     *  【0 15 10 ? * 1-6】 每个月的周一至周六10:15分执行一次
     *  【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
     *  【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
     *  【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次；
     */
	//@Scheduled(cron = "0 * * * * MON-SAT")
	
	//周一到周六, 不管是哪一分、哪一小时、哪一天, 都会在 0,1,2,3,4 这几秒运行一次!
	@Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT") 
	public void hello(){
		
		System.out.println("Hello .....");
	}

}
