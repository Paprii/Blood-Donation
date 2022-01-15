package com.example.helpandfeedback;

public class Profile {


        String name;
        String help;

        /**
         * Constuctor of 'profile' class
         * @param name
         * @param help
         */
        public Profile(String name, String help) {
            this.name = name;
            this.help = help;
        }

        /**
         * GET method()
         * @return name
         */
        public String getName() {

            return name;
        }

        /**
         *  Get method()
         * @return help
         */

        public String getHelp() {
        return help;
            }
}

