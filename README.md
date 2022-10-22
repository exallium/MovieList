# MovieList

This is a simple application meant to go along with the blog post located [here](https://www.ahart.dev/blog/how-to-fill-knowledge-gaps).

## Building

You will need to go over to [The movie database](https://themoviedb.org) and register an account to get
an api key. This can then be added to a local `.properties` file.

```
movies.api.key=<my-api-key>
```

You can save this to any `.properties` file in the root folder of the repository, so long as it is not
commited and published to GitHub, you just need to update what file we read from in `app/build.gradle`.


## Usage

The app in and of itself does not perform anything useful, but its git history does. Use the history to explore
each step of building the app out from scratch.

Once you have your head around what the app is doing, try adding things. Add a details screen. Add more tests.
Add swipe to refresh. Whatever you're trying to learn, I hope this can serve as a basis for doing so.

My recommendation is to fork it to your github account and go wild! I will be upating this with new functionality
that I can point other articles at in the future, but I will do my best to maintain a cohesive and sensible git
history.
