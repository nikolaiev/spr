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
<b>Laory has 500 on his account. Try to buy several times some of tickets in order to make his account empty</b>
<br>
Events UI <a href="/events/all/">All Events</a>
<br><br>
Auditoriums UI <a href="/auditoriums/all/">All Auditoriums</a>

<br>
<br>
<br>

<pre>
    1. Add new entity to the application - UserAccount, it should store the amount of prepaid money user has in the system, which should be used during booking procedure.
        Add methods for refilling the account to the BookingFacade class.
        Add DAO and service objects for new entity. Add ticketPrice field to Event entity.
        - All was done. See UserAccountService, UserAccount, UserAccountDAO


    2. Update ticket booking methods to check and withdraw money from user account according to the ticketPrice for particular event.
        - See BookingController.bookTicket method

    Getting booked tickets for particular event should be accessible only to users with role BOOKING_MANAGER.
        - beans.controllers.PdfController method getTicketsByEventsPDF(...)

    3.  Configure appropriate PlatformTransactionManager implementation in Spring application context.
        - Was done since 1-st task. See DbSessionFactory.class

    4.  Make ticket booking methods transactional using Spring declarative transactions management (either xml or annotation based config).
        - Was done since 1-st task (all repos @Transactional). But were updated @Transactional for some methods.
        - See BookingController, BookingFacadeController, BookingServiceImplemetation
</pre>
