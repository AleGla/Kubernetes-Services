package com.ar.alegla.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvironmentVariables {


		@Value("${cacheDirectory}")
		private String cacheDirectory;
		
		@Value("${scheduledDeleteOldData}")
		private String scheduledDeleteOldData;

		public String getCacheDirectory() {
			return cacheDirectory;
		}

		public void setCacheDirectory(String cacheDirectory) {
			this.cacheDirectory = cacheDirectory;
		}

		public String getScheduledDeleteOldData() {
			return scheduledDeleteOldData;
		}

		public void setScheduledDeleteOldData(String scheduledDeleteOldData) {
			this.scheduledDeleteOldData = scheduledDeleteOldData;
		}
		
		

		
}
