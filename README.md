# Startingpoint For Re/Frame (ClojureScript) Web App

* pulls in NPMs
* pulls in react-semantic-ui
* hooked in media-queries
* packs everything using inliner

## Quick Start

```
lein clean
npm install
npm run build
lein figwheel dev
```

## Development Mode

### Bring in NPM dependency

* (optionally) amend `package.json`
* change `resources\public\js\webpack\main.js`
* change `resources\extern.js`

Download NPM dependencies:

```
npm install
```

### Rebuild LESS

Less file is in `src\less\main.less`

Dependencies loaded through `resources\public\js\webpack\main.js`

All built into `resources\public\js\bundle.js`

Rebuild bundle with:

```
npm run build
npm run watch
```

### Dev Run Application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Run tests

Make sure to build a fresh `bundle.js`.

```
lein clean
lein cljsbuild once test
```

Can append test command to run: one from `project.clj` `:test-commands`

## Production Build

To compile clojurescript to javascript:

```
lein clean
npm run build
lein cljsbuild once min
./pack.ps1
```

The `./pack.ps1` step requires *PowerShell* (Windows) and the NPM *inliner* module (see section below).

Once the *inliner* runs find output as single-file in `resources/public/kundel.html`.

## Inline HTML

Install 'inliner' module globally.

```
npm install -g inliner
```

Run the inliner; two versions, second one for PowerShell:

```
inliner -m resources\public\index.html > resources\public\kundel.html
inliner -m resources\public\index.html | Out-file -Encoding ASCII "resources\public\kundel.html"
```

## REPL Examples (Debugging)

### Connect to REPL (command-line) from IntelliJ/Cursive

Our 'project.clj' sets up figwheel :nrepl-port to 7002.

Create remote REPL run configuration to 127.0.0.1:7002.

Once started paste the following in the IntelliJ REPL (Alt-8)

```
(use 'figwheel-sidecar.repl-api)
(cljs-repl)
```

### REPL Reload Events (Example)

```
(in-ns 'kundel.core)
(:require '[kundel.events]
          '[re-frame.core :as rf]  :reload))
```

Above `:mock-db` is a new event added in kundel.events that we want to take effect

### REPL Run Single Test

Start 'dev' profile with figwheel:

```
lein figwheel dev
```

Run one test from 'kundel.test' namespace:

```
(in-ns 'kundel.core)
(enable-console-print!)
(require '[debux.cs.core :refer-macros [clog dbg break]] :reload)
(require '[kundel.test :as t] :reload)
(t/test-fn)
```

To run all tests, just (run-tests)

### REPL Debug Trace a Method

Start 'dev' profile with figwheel:

```
lein figwheel dev
```

Load 'debux' and debug a function.

```
(require '[debux.cs.core :refer-macros [clog dbg break]])
(dbg (+ 1 2))
```

### Debugging in Code

Add the following `:require` macro to your `ns`:

```
(:require [debux.cs.core :refer-macros [clog dbg break]]))
```

Add `(dbg ...)` statements wherever in code

### Inspecting/Modifying app-db in REPL

We have an event:

```
(rf/reg-event-db
  :ev:f
  (fn [db [_ f]]
    (f db)))
```

We can modify app-db in REPL:

```
(re-frame/dispatch-sync [:ev:f #(update-in %
                                           [:data :users :Eva :categories 3]
                                           (fn [rec] (assoc-in rec [:start] "2017-01-10")))])
```

We can inspect app-db:

```
@re-frame.db/app-db
```

Or bring in the DB

```
(:require [re-frame.db :as db])
```

And:

```
(reset! db/app-db (assoc-in @db/app-db [..] ..))
```
