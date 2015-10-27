// ******************************************************************
//
// Copyright 2015 Samsung Electronics All Rights Reserved.
//
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

package oic.ctt.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public final class CTLogger {
    private static LogConfiguratorJava sLogConfigurator = new LogConfiguratorJava();
    private static final String        CONFIG_FILE_NAME = "logger.config";

    public static Logger getInstance() {
        final Logger logger = LoggerFactory.getLogger("CTLogger");
        configureLog();
        return logger;
    }

    private static void configureLog() {

        try {
            sLogConfigurator.readConfigurationFromFile(CONFIG_FILE_NAME);

        } catch (FileNotFoundException ex) {
            sLogConfigurator.setDefaultConfiguration();
            System.err
                    .println("Error: Logger configuration file not found, using default settings");
            // ex.printStackTrace();
        } catch (IOException ex) {
            sLogConfigurator.setDefaultConfiguration();
            System.err.println("Error: IoException, using default settings");
            ex.printStackTrace();
        }

        sLogConfigurator.configure();
    }

}
