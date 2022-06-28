# Group Up

## Description

Group Up is a web application designed with the average video game enthusiast in mind. In today's modern age, playing video games can sometimes become a negative and hostile environment
for some. The overwhelming sense of pressure to perform in competitive fps games, the frustration of having bad teammates, and the poor use of communication has filled riddled the community
for too long. I designed Group Up with all of this in mind. A web application where you can come and follow your favorite games, create a new team and post in team threads to share your
ideas, thoughts, and strats with one another. This is a safe place where all walks of life can come and find the right team for them so they can enjoy playing their favorite games and
make new friendships. 

## Schema

![dbschema](src\images\schema.PNG)



## Usage

1. Upon landing on the home screen you are greeted with the Group Up motto and video background.<br> 
<img src="src\images\landing.PNG" width="700" ><br>


2. If you are a new user click the signup button to be taken to the registration page. You will be asked to enter your Email, Username, and a Password (passwords must be 8 characters in length and include at least 1 special character, 1 number, 1 uppercase character, and 1 lowercase character)<br>
<img src="src\images\register.PNG" width="700" ><br>


3. Once you have completed registering check your email address for a verification email link. Click the link to verify your account and come back to the login page to start your journey. 
BE ADVISED: If you DO NOT click the verification link in the email sent to your account you will not be able to get access to the website. If you have entered a wrong email or no longer have access to your email, email customerservice@groupup.com for help.<br>
<img src="src\images\login.PNG" width="700" ><br>


4. Once you login you will be taken to your profile page. Due to the application being in beta status all users will have the same icon regardless of age/gender/race. In future updates we will be including the use of avatar icons that you can choose.<br>  
<img src="src\images\profilepage.PNG" width="700" ><br>


5. Check out all of the games currently saved in the database by clicking the view all games tab on the header. Here you can see all of the current games that are being followed by our users. Feel free to view the game page to find any teams for that specific game or read more details about the game.<br>  
<img src="src\images\viewgames.PNG" width="700" ><br> 
<img src="src\images\gamepage.PNG" width="700" ><br> 

6. Check out all of the current teams created by clicking the view all teams tab on the header. Here you can view all current team requests based by the each console (PC, Xbox, Playstation, Nintendo Switch). You can click on the team name tags to jump straight to the team page where you can comment/join/and view the current members of the team.<br>  
<img src="src\images\viewteams.PNG " width="700" ><br> 


7. Can't find a game you want to play? Click the search all games tab on the header to look up over 1k different video games with our 3rd party service provider, RAWG Api. Once you find the game you want to play click the save game button. You will be taken straight back to the view all games page where you can go ahead and click the follow button on the game to save it to your profile.<br>  
<img src="src\images\searchgames.PNG" width="700" ><br> 


8. Want to lead your own team? Go to the create team tab on the header. Here you will enter the name of your team, the game you want to play from the list of available games in the db, select the amount of players you want on your team, and finally select the console you are playing on. Once you have created a team you will be taken straight to the team page where you can view all of the information that you have created.<br>  
<img src="src\images\createteam.PNG " width="700" ><br> 

<br> 

##Technology Used

1. Backend: SpringBoot

2. Maven Dependencies: Junit, Mapstruct, Lombok, Thymeleaf, OkHttp, Spring-Security

3. Frontend: Angular, CSS, HTML, Bootstrap, Typescript, Javascript

4. Database:  MariaDB


## Credits

-Alex Cormier - Lead developer/designer/tester 

-Cory Roberts - Assisted in problems and issues that arised during development


