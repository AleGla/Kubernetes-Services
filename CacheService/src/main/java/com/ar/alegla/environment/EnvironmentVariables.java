package com.ar.alegla.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvironmentVariables {


		@Value("${cacheDirectory}")
		private String cacheDirectory;
		
		@Value("${lifeTimeOfData}")
		private long lifeTimeOfData;

		public String getCacheDirectory() {
			return cacheDirectory;
		}

		public void setCacheDirectory(String cacheDirectory) {
			this.cacheDirectory = cacheDirectory;
		}

		public long getLifeTimeOfData() {
			return lifeTimeOfData;
		}

		public void setLifeTimeOfData(long lifeTimeOfData) {
			this.lifeTimeOfData = lifeTimeOfData;
		}

		
		
		

		
}
