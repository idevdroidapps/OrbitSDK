// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "OrbitSDK",
    platforms: [
        .iOS(.v17)
    ],
    products: [
        .library(
            name: "OrbitSDK",
            targets: ["OrbitSDK"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "OrbitSDK",
            path: "./OrbitSDK.xcframework"
        ),
    ]
)
