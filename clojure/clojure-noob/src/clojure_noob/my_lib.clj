(ns clojure-noob.my-lib)

;; current name space
(ns-name *ns*)

(def great-books ["East of Eden" "The Glass Bead Game"])

(ns-interns *ns*)
; => {great-books #'user/great-books}
(ns clojure-noob.my-lib)

;; current name space
(ns-name *ns*)

;; definitions
(def great-books ["East of Eden" "The Glass Bead Game"])

;; show namespace contents
(ns-interns *ns*)
(ns-interns 'clojure-noob.my-lib)
;; {great-books #'clojure-noob.my-lib/great-books}

;; create namespace
(create-ns 'clojure-noob.never-use)
;; jump into another namespace
(in-ns 'clojure-noob.clojure-core)
;; dereference
(clojure.core/let [test (clojure.core/deref #'clojure-noob.my-lib/great-books)])
(clojure.core/let [test clojure-noob.my-lib/great-books])

;; refer to other namespace
(in-ns 'clojure-noob.my-other-lib)
(clojure.core/refer 'clojure-noob.my-lib)
(clojure.core/let [test great-books])
;; refer with modification
(clojure.core/refer 'clojure-noob.my-lib :exclude ['great-books])
(clojure.core/refer 'clojure-noob.my-lib :only ['great-books])
(clojure.core/refer 'clojure-noob.my-lib :rename {'great-books 'good-books})

;; define a function available to same namespace
(clojure.core/defn- private-function
  "Just an example function that does nothing"
  [])

;; alias
(clojure.core/alias 'core 'clojure.core)
(core/let [test "test"])
