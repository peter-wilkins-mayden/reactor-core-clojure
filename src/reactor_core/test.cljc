;
; Copyright 2018 the original author or authors.
;
; Licensed under the Apache License, Version 2.0 (the "License")
; you may not use this file except in compliance with the License.
; You may obtain a copy of the License at
;
;      http://www.apache.org/licenses/LICENSE-2.0
;
; Unless required by applicable law or agreed to in writing, software
; distributed under the License is distributed on an "AS IS" BASIS,
; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
; See the License for the specific language governing permissions and
; limitations under the License.
;

(ns
  ^{:doc    "A unit testing support."
    :author "Vladimir Tsanev"}
  reactor-core.test)

#?(:clj
   (defmacro async
     "Support async tests like cljs.test/async on jvm"
     [done & body]
     `(let [p# (promise)
            ~done (fn []
                    (if (realized? p#)
                      (println "WARNING: Async test called done more than one time.")
                      (deliver p# nil)))]
        ~@body
        @p#)))
