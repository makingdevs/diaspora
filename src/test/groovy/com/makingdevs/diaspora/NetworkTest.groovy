package com.makingdevs.diaspora

import org.junit.Test

class NetworkTest {

  def network = Network.instance

  @Test
  void commonFollowersTest(){
    def user1 = new User(username: "neodevelop", followers: [new User(username: "makingdevs"), new User(username: "3rickDJ"), new User(username: "glaforge")])
    def user2 = new User(username: "carlogilmar", followers: [new User(username: "3rickDJ"), new User(username: "glaforge"), new User(username: "graemerocher")])

    assert ['3rickDJ', 'glaforge'] == network.commonFollowersFor(user1, user2)*.username
  }

  @Test
  void followingUsersTest(){

    def user3 = ["3rickDJ": ["user8", "graemerocher", "josevalim"]]
    def user4 = [glaforge: ["josevalim", "graemerocher", "johnmccarthy"]]
    def user5 = [graemerocher: ["3rickDJ", "matsumoto", "glaforge"]]

    def grid = [user3, user4, user5]

    assert [
      "3rickDJ": ["graemerocher"],
      glaforge: ["graemerocher"],
      graemerocher: ["3rickDJ", "glaforge"]
    ] == network.gridOfUsersAndFollowers(grid)

  }

  @Test
  void graphvizTest(){
    def user1 = [neodevelop: ["makingdevs", "3rickDJ", "glaforge"]]
    def user2 = [carlogilmar: ["3rickDJ", "glaforge", "graemerocher"]]

    def user3 = ["3rickDJ": ["user8", "graemerocher", "josevalim"]]
    def user4 = [glaforge: ["josevalim", "graemerocher", "johnmccarthy"]]
    def user5 = [graemerocher: ["3rickDJ", "matsumoto", "glaforge"]]

    def grid = [user3, user4, user5]

    def graphCommonFollowers = network.graphvizForFollowers(user1, user2)
    def graph = network.gridOfUsersAndFollowers(grid)
//     assert "user1 -> user3;\nuser2 -> user3;\nuser1 -> user4;\nuser2 -> user4;".trim() == graphCommonFollowers.trim()
    // assert "user3 -> user5;\nuser4 -> user5;\nuser5 -> user3;\nuser5 -> user4;".trim() ==  network.graphvizForFollowersOfFollowers(graph).trim()

    println graphCommonFollowers.trim()
    println network.graphvizForFollowersOfFollowers(graph).trim()
  }
}
