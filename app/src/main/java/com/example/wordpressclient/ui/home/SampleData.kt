package com.example.wordpressclient.data

import java.io.Serializable

data class Article(
    val title: String,
    val author: String,
    val date: String,
    val views: String,
    val imageUrl: String,
    val content: String
) : Serializable

val sampleArticles = listOf(
    Article(
        title = "New VR Headsets That Will Shape the Metaverse",
        author = "Mason Eduard",
        date = "Sep 20, 2025",
        views = "8686 views",
        imageUrl = "https://picsum.photos/600/400?random=1",
        content = "The COVID-19 pandemic has triggered an unprecedented demand for virtual reality (VR) headsets and gear. According to the IDC, global shipments of AR and VR headsets increased by a staggering 348.4% in 2021, of which standalone VR headsets occupied nearly 90% of the market share. The organisation also estimates that this growth momentum will continue until 2025. One of the emerging concepts that could offer brand new use cases for VR headsets is the metaverse. The metaverse, currently being developed by companies like Meta or Microsoft, is an integrated immersive world where there are different spaces for tasks like work, play, art, and content consumption. Users can move freely in and out of these spaces, and the metaverse will generate a self-sustained VR economy. While the metaverse is still a few years away, companies are coming out with next-gen VR devices that will push the boundaries of innovation. They are designed to provide users with incredibly immersive experiences so that they can make the most of the rising communications platform. Some of these include: Apple’s new AR/VR headset (rumored) Apple’s new headset has been in the rumor mill for a while. It is intended to be a VR headset with added augmented and mixed reality (AR/MR) capabilities distinct from Apple’s AR glasses, which is also under development. Unlike most headsets, the device will have several cameras and sensors embedded on the external chassis so that it can process inputs from the real-world environment. It will also be significantly light (around 150 grams) and could cost upwards of \$2000. Apple’s VR headset was originally slated for release in 2022 but has recently run into supply chain issues as per Bloomberg. We could now expect it sometime later this year or in early 2023. Google’s Project Iris The early details of Google’s Project Iris appeared in January 2022, and the device is yet to be formally announced. It is not a pure-play virtual reality headset, but instead carries on the legacy of 2012’s Google Glass product. Google already has a large team working on the device and its job listings indicate that it plans to hire many more. Given Google’s massive data center infrastructure and content capabilities, it is well poised to make its own metaverse play and take on Microsoft and Meta."
    ),
    Article(
        title = "AI Assistants Transforming Daily Workflows",
        author = "Sophia Nguyen",
        date = "Sep 18, 2025",
        views = "5421 views",
        imageUrl = "https://picsum.photos/600/400?random=2",
        content = "The COVID-19 pandemic has triggered an unprecedented demand for virtual reality (VR) headsets and gear. According to the IDC, global shipments of AR and VR headsets increased by a staggering 348.4% in 2021, of which standalone VR headsets occupied nearly 90% of the market share. The organisation also estimates that this growth momentum will continue until 2025. One of the emerging concepts that could offer brand new use cases for VR headsets is the metaverse. The metaverse, currently being developed by companies like Meta or Microsoft, is an integrated immersive world where there are different spaces for tasks like work, play, art, and content consumption. Users can move freely in and out of these spaces, and the metaverse will generate a self-sustained VR economy. While the metaverse is still a few years away, companies are coming out with next-gen VR devices that will push the boundaries of innovation. They are designed to provide users with incredibly immersive experiences so that they can make the most of the rising communications platform. Some of these include: Apple’s new AR/VR headset (rumored) Apple’s new headset has been in the rumor mill for a while. It is intended to be a VR headset with added augmented and mixed reality (AR/MR) capabilities distinct from Apple’s AR glasses, which is also under development. Unlike most headsets, the device will have several cameras and sensors embedded on the external chassis so that it can process inputs from the real-world environment. It will also be significantly light (around 150 grams) and could cost upwards of \$2000. Apple’s VR headset was originally slated for release in 2022 but has recently run into supply chain issues as per Bloomberg. We could now expect it sometime later this year or in early 2023. Google’s Project Iris The early details of Google’s Project Iris appeared in January 2022, and the device is yet to be formally announced. It is not a pure-play virtual reality headset, but instead carries on the legacy of 2012’s Google Glass product. Google already has a large team working on the device and its job listings indicate that it plans to hire many more. Given Google’s massive data center infrastructure and content capabilities, it is well poised to make its own metaverse play and take on Microsoft and Meta."
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
