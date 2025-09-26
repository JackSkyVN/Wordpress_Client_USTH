package com.example.wordpressclient.data

data class Article(
    val title: String,
    val author: String,
    val date: String,
    val views: String,
    val imageUrl: String,
    val content: String
)

val sampleArticles = listOf(
    Article(
        title = "New VR Headsets That Will Shape the Metaverse",
        author = "Mason Eduard",
        date = "Sep 20, 2025",
        views = "8686 views",
        imageUrl = "https://picsum.photos/600/400?random=1",
        content = "This is a detailed article about how new VR headsets are shaping the metaverse. ..."
    ),
    Article(
        title = "AI Assistants Transforming Daily Workflows",
        author = "Sophia Nguyen",
        date = "Sep 18, 2025",
        views = "5421 views",
        imageUrl = "https://picsum.photos/600/400?random=2",
        content = "AI assistants are becoming essential in modern workflows, automating tasks and boosting productivity. ..."
    ),
    Article(
        title = "Top 10 Cybersecurity Trends in 2025",
        author = "James Carter",
        date = "Sep 15, 2025",
        views = "7133 views",
        imageUrl = "https://picsum.photos/600/400?random=3",
        content = "Cybersecurity in 2025 is defined by zero-trust models, AI-driven defenses, and enhanced privacy concerns. ..."
    ),
    Article(
        title = "Green Tech Innovations Saving the Planet",
        author = "Linh Tran",
        date = "Sep 12, 2025",
        views = "4890 views",
        imageUrl = "https://picsum.photos/600/400?random=4",
        content = "Green technologies are revolutionizing energy, waste management, and transportation to save the planet. ..."
    ),
    Article(
        title = "5G Expansion and Its Global Impact",
        author = "David Johnson",
        date = "Sep 10, 2025",
        views = "6250 views",
        imageUrl = "https://picsum.photos/600/400?random=5",
        content = "5G networks are expanding rapidly, driving innovation in IoT, autonomous vehicles, and smart cities. ..."
    )
)
