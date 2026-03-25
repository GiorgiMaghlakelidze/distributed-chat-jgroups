# Distributed chat Jgroups

A simple peer-to-peer console chat application built in Java using JGroups.  
This project connects multiple peers into a shared cluster and allows them to exchange messages in real time through a GossipRouter-based setup.

## Features

- Peer-to-peer group messaging
- Cluster-based communication using JGroups
- Join/leave notifications
- Console-based chat input and output
- GossipRouter support for peer discovery and communication

## How It Works

The application starts a JGroups channel and connects to a cluster named `PeerCluster`.  
Each peer can type messages into the console, and all connected peers receive them.

The program also detects when a user joins or leaves the chat and prints a notification.

## Technologies Used

- Java
- JGroups
- SLF4J / Logger

## Project Structure

- `PeerMain.java` — application entry point, creates the JGroups channel and connects to the cluster
- `RumourHandler.java` — handles receiving messages, membership changes, and sending user input as chat messages

## Requirements

- Java 17+ (or your version)
- JGroups
- SLF4J
- A running GossipRouter instance

## Configuration

The application currently uses the following fixed configuration:

- Cluster name: `PeerCluster`
- GossipRouter host: `192.168.1.162`
- GossipRouter port: `12001`

You may need to change the router hostname to match your own network setup before running the project.

## Running the Project

1. Start the GossipRouter
2. Run the application on one or more terminals/devices
3. Type messages in the console to chat with other peers

## Example

When a peer joins:
```text
You joined the chat successfully!
