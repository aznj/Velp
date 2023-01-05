# Velp - Multi Module MVVM Architecture

Steps to build the app
1. In Android Studio, File -> New -> Project from Version Control.
2. Paste this url https://github.com/aznj/Velp.git inside URL column. Click Clone.
3. Wait until project is loaded. Try delete .idea and .gradle file if you can't open it. Close the project and reopen it again after deleted .idea and .gradle.
4. Build/Run the app.

In this coding challenge, I used multi module with MVVM Architecture. Each major features for example search and get business details has its own module. I try to implement clean architechture by separating each module to 3 different layers at best which is presentation layer, domain layer and data layer. Domain layer as the middle layer that depended by data and presentation layer. This way I can avoid presentation from being able to access data layer directly and vice versa. Domain layer contain use cases for each features for example search business and get business details. I used Hilt for dependency injection. UI might not be the best in this project but I hope you can see my effort on applying SOLID principles in the source code. I would love to get feedback from you regarding my code. Feel free to reach me at azlanj1989@gmail.com. Thank you.
