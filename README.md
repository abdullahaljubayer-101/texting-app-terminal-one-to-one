# 💬 Texting App (Terminal, One-to-One)

![Java](https://img.shields.io/badge/Language-Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Sockets](https://img.shields.io/badge/Networking-TCP%20Sockets-blueviolet?style=flat-square)
![Type](https://img.shields.io/badge/Type-CLI-lightgrey?style=flat-square)

A simple **two-way chat in the terminal**, built with plain **Java sockets**. Start the server in one terminal and the client in another, and they can text each other back and forth in real time. 🖥️↔️🖥️

This is the first version of my chat app series, where I learned how **client–server networking and threads** work in Java.

---

## ✨ Features

- ⚡ Real-time messages between a server and a client
- 🧵 Separate threads for **sending** and **receiving**, so both sides can type at any time
- 🏷️ Messages are labeled `Server:` or `Client:` so you know who said what
- 📦 Zero dependencies, just the JDK

## 🧠 How it works

```text
Terminal 1                      Terminal 2
┌─────────────┐  TCP :1234   ┌─────────────┐
│  Server.java │ ◄──────────► │ Client.java  │
└─────────────┘               └─────────────┘
```

- `Server.java` opens a `ServerSocket` on port **1234** and waits for one client.
- `Client.java` connects to `localhost:1234`.
- Each side runs one thread that reads incoming messages and one that sends what you type.

## 🚀 Run it

```bash
cd src
javac *.java
```

**Terminal 1:**

```bash
java Server
```

**Terminal 2:**

```bash
java Client
```

Now type in either terminal and press Enter. 💬

## 🔗 Related projects

- 👥 [Texting App: Terminal, One-to-Many (group chat)](https://github.com/abdullahaljubayer-101/texting-app-terminal-one-to-many)
- 🪟 [Texting App: GUI, One-to-One (JavaFX)](https://github.com/abdullahaljubayer-101/texting-app-gui-one-to-one)

---

Made by **Abdullah Al Jubayer** · [GitHub](https://github.com/abdullahaljubayer-101) · [Portfolio](https://abdullahaljubayer.com)
