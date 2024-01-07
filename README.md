# Artwork Management System

## Overview
The Artwork Management System is a sophisticated tool designed to enhance the interaction between users and a vast collection of artworks. Through a console-based interface, 
the system allows users to search for artworks by name, artist, or genre, utilizing an intuitive auto-complete feature that predicts and suggests results as they type. The 
system is also equipped with a recommendation engine that suggests similar artworks based on user preferences, providing a personalized art exploration experience.

**Contributors: Group project for Rubo Xing, Felix Yuzhou Sun, and Zhangliang Yang**

## Features
- **Auto-complete Search**: Search for artworks or artists with a predictive text feature that makes finding art fast and intuitive.
- **Search by Genre**: Explore artworks sorted by genre for a targeted browsing experience.
- **Artwork Recommendations**: Receive suggestions for similar artworks, allowing you to discover new art related to your interests.
- **Detailed Artwork Information**: View comprehensive details about each artwork, including title, author, genre, century of creation, and medium.
- **External Links**: Access extended information through provided Wikipedia links for each artwork.
- **Console-based Navigation**: Navigate through the system using a simple and interactive menu-driven interface.

## File Structure
├── src\
│ ├── data\
│ │ ├── Artwork\
│ │ ├── Author\
│ │ ├── Data\
│ │ └── Recommender\
│ ├── main\
│ │ ├── AutoCompleteUserInterface\
│ │ ├── Pictures\
│ │ └── SystemController\
│ └── trie\
│ ├── Node\
│ ├── Term\
│ └── Trie\
├── pic\
│ └── Project.png\
└── External Libraries\

## Getting Started
To run the Artwork Management System, follow these steps:

1. Ensure you have [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) installed on your system.
2. Clone the repository to your local machine.
3. Navigate to the `src/main` directory.
4. Compile `SystemController.java` using `javac SystemController.java`.
5. Run `SystemController` using `java SystemController`.

## Usage
Once the program is running, follow the on-screen prompts to navigate through the system. Use the search features to explore the artwork collection and receive recommendations.

## Contributing
Contributions to the Artwork Management System are welcome. Please fork the repository and submit a pull request with your features or fixes.
