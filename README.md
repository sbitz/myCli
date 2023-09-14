# MyCli

Terrible name. Not very useful. Written in java.

This example CLI application demonstrates input and output from file or standard out. I also demonstrate the behavior 
pattern to enable the addition of plugins that implement the `InputHandler` interface to enable easy extension of the 
application to provide additional behavior.

## Installation

Create the jar with maven.

```bash
mvn clean package
```

Copy it to your preferred location
```bash
export YOUR_PATH=...
cp target/myCli-1.0.jar $YOUR_PATH/ 
alias myCli='java -cp $YOUR_PATH/myCli-1.0.jar com.sbitz.PluginDriver'
```

Execute
```bash
myCli
```

## Usage

The default behavior is to read from stdin until it encounters "hello" alone on a line. The `InputPlugin` simply prints 
out each line. The `EchoPlugin` will print the input twice. It's not useful, but it demonstrates through the use of 
`PluginDriver` that you can choose from any of the available plugins. 

Eventually, I would improve on the reflection or loading of plugin choices. Currently, the `META-INF/services` directory
contains a file named after the `InputHandler` interface. The lines of the file are the FQ names of the concrete classes
implementing the interface, and Java's `ServiceLoader` is used to discover and instantiate the classes, as chosen by the 
user input at runtime.

Change the input to use a file with the `-in` argument, and redirect output with `-out`.

```bash
myCli -in $IN_FILE -out $OUT_FILE
```

## Incorporating plugins
To add a plugin...

1. Extend `com.sbitz.InputHandler` in another project.
2. Add your plugin to the `META-INF/services/com.sbitz.InputHandler` file with your new concrete class name.
3. Add the plugin **to the beginning** of the classpath (with `:` separator on linux, `;` on windows)
4. The next invocation of `myCli` will read service definitions from both jars and will be available as an option to run.