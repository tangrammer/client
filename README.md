# Client:  emacs + nrepl + cljs + brepl + austin + core.async
A ClojureScript app to demonstrate the development workflow of clojurescript files using Emacs.

This is an example of project configuration to work with clojurescript in the same way that you'll do if you work with clojure. 
That's to say once you follow the steps to link your brepl to your nrepl, then you can evaluate your cljs files and directly see the result on the browser 

[VIDEO](https://vimeo.com/77124644) 

## Requirements
I'm using in this project jekyll as webserver so you'll need ruby >= 1.8 


## Usage

**Steps in shell**

* cd into project
* lein cljsbuild once base
* cd site 
* jekyll serve --watch

**Steps in Emacs**   

* Open File: project.clj   
* M-X nrepl-jack-in
* write into nrepl session: `(def repl-env (reset! cemerick.austin.repls/browser-repl-env
                      (cemerick.austin/repl-env)))`
* adapt the sout `Browser-REPL ready @ http://localhost:XXXXX/ZZZ/repl/start` to the js script in site/index.html: `<script>;goog.require('clojure.browser.repl');clojure.browser.repl.connect.call(null, 'http://localhost:XXXX/ZZZ/repl');</script>`. You only have to change the url of the brepl http://localhost:**XXXXX/ZZZ**/repl/start
* write into nrepl session: `(cemerick.austin.repls/cljs-repl repl-env)`
* reload browser (jekyll default port 4000): [http://localhost:4000](http://localhost:4000)
* now you can `C-K` to compile the cljs files into the brepl and evaluate your code in the browser with `C-E`, and `C-C M-N`



## Based on these tools  
* [nrepl](https://github.com/clojure/tools.nrepl)
* [austin](https://github.com/cemerick/austin)


## TODO: 
Currently austin/the_app can use the core.async macros 


## License

Copyright © 2013 @tangrammer

Distributed under the Eclipse Public License version 1.0 
