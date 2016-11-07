//package stats.bootstrap;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import stats.domain.Season;
//import stats.repositories.SeasonRepository;
//
//@Component
//public class SeasonLoader implements ApplicationListener<ContextRefreshedEvent> {
//	
//	private SeasonRepository seasonRepository;
//	
//	private Logger log = Logger.getLogger(SeasonLoader.class);
//			
//	@Autowired
//	public void setSeasonRepository(SeasonRepository seasonRepository) {
//		this.seasonRepository = seasonRepository;
//	}
//
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		Season season1 = new Season();
//		season1.setSeason_number(1);
//		season1.setSeason_name("Winter 2015");
//		
//		seasonRepository.save(season1);
//		
//		log.info("Saved Season - number: " + season1.getSeason_number());
//		
//		Season season2 = new Season();
//		season2.setSeason_number(2);
//		season2.setSeason_name("Spring 2015");
//		
//		seasonRepository.save(season2);
//		
//		log.info("Saved Season - number: " + season2.getSeason_number());
//		
//	}
//
//}
