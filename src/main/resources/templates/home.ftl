<h4><a href="/logout">logout</a></h4>

<h1>Spring MVC application</h1>

Visit <a href="/swagger-ui.html">SWAGGER</a> page to resolve all available routes.
<br>
<br>
Root project folder contains <b>events.json</b> , <b>users.json</b> file to be processed via batch (you can find batch-controller in Swagger)
<br>
Batch users or events <a href="/batch">Batch UI</a>
<br>
<br>
Get all Tickets for All users <a href="/pdf/users">All tickets generate pdf</a>
<br><br>
<span style="color: #ff0000;">BOOKING_MANAGER only path</span>
Get specific filtered tickets <a href="/pdf/events?event=The%20revenant&auditorium=Yellow%20hall&date=05-02-2016%2021%3A18">generate pdf</a>
<br><br>
Users UI <a href="/users/all/">All Users</a>
<br><br>
Events UI <a href="/events/all/">All Events</a>
<br><br>
Auditoriums UI <a href="/auditoriums/all/">All Auditoriums</a>

<br>
<br>
<br>

<pre>
    1. Configure Spring Security for ticket booking web application - add DelegatingFilterProxy to web.xml
        - beans.configuration.WebSecurityConfig.java


    2. Configure access control via security namespace. All application operations should be accessible to users with role RESGISTERED_USER only.
        - beans.models.MyUserPrincipal
        - beans.models.UserRole
        - beans.services.UserServiceImpl
        - beans.configuration.WebSecurityConfig.java

    Getting booked tickets for particular event should be accessible only to users with role BOOKING_MANAGER.
        - beans.controllers.PdfController method getTicketsByEventsPDF(...)

    Add two new fields to User entity - password and roles. Roles field should contain comma-separated list of user roles. All users in database should have REGISTERED_USER role by default. Create several test users with additional BOOKING_MANAGER role.
        - beans.models.User
        - beans.models.UserRole

    3. Implement form-based login via security namespace, add custom login page, configure DAOAuthenticationProvider and UserDetailsService to load user data from database. Configure logout filter.
        - beans.configuration.WebSecurityConfig.java DAOAuthenticationProvider
        - beans.services.UserServiceImpl
        - resources/templates/login

    4. Configure Remember-Me authentication.
        - beans.configuration.WebSecurityConfig.java
        - resources/schema.sql

    5. Implement password encoding during authentication.
        - beans.configuration.WebSecurityConfig.java PasswordEncoder
</pre>
