package com.makingdevs.diaspora

import wslite.rest.*

@Singleton
class GithubClient {

  String urlBase = "https://api.github.com"
  String API_KEY = System.getenv("GITHUB_API_KEY")
  Integer pageSize = 100

  User getUser(String username) {
    RESTClient client = new RESTClient(urlBase)
    def response = client.get(path:"/users/$username", headers: ["Authorization" : "token $API_KEY"])
    assert 200 == response.statusCode
    new User(id: response.json.id, username: response.json.login, followersSize: response.json.followers)
  }

  User putFollowersToUser(User user) {

    Integer pages = (user.followersSize / pageSize).toInteger() + (user.followersSize % pageSize ? 1 : 0 )

    RESTClient client = new RESTClient(urlBase)
    List followers = []
    (1..pages).step(1) { page ->
      def response = client.get(
        path:"/users/${user.username}/followers",
        headers: ["Authorization" : "token $API_KEY"],
        query: ["per_page": pageSize, page: page])
      assert 200 == response.statusCode

      followers += response.json.collect { doc ->
        new User(id: doc.id, username: doc.login, followersSize: doc.followers)
      }
    }
    user.followers = followers
    user
  }
}
