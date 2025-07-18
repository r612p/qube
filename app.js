// 1. Basic setup
const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(
  45,
  window.innerWidth / window.innerHeight,
  0.1,
  1000
);
camera.position.set(0, 40, 30);
camera.lookAt(0, 0, 0);

const renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

// 2. Lighting (optional but makes it nicer)
const light = new THREE.DirectionalLight(0xffffff, 1);
light.position.set(0, 1, 1);
scene.add(light);

// 3. Create plane geometry
const planeWidth = 150;
const planeHeight = 100;

const segments = 40; 
const geometry = new THREE.PlaneGeometry(planeWidth, planeHeight, segments, segments);
geometry.rotateX(-Math.PI / 2); // rotate plane flat

// 4. Add vertex colors attribute for colored waves
const colors = [];
for (let i = 0; i < geometry.attributes.position.count; i++) {
  colors.push(0, 0, 1); // initial color blue
}
geometry.setAttribute('color', new THREE.Float32BufferAttribute(colors, 3));

// 5. Material with vertex colors enabled
const material = new THREE.MeshPhongMaterial({
  vertexColors: true,
  side: THREE.DoubleSide,
  wireframe: true,  // This draws grid lines on your plane
  wireframeLinewidth: 2,
});

// 6. Create mesh and add to scene
const plane = new THREE.Mesh(geometry, material);
scene.add(plane);

// 7. Animate waves
let clock = new THREE.Clock();

function animate() {
  requestAnimationFrame(animate);

  const time = clock.getElapsedTime();
  const position = geometry.attributes.position;
  const color = geometry.attributes.color;

 const frequency = 15;     // high frequency = more wave detail
const amplitude = 12;     // high amplitude = taller waves
const speed = 0.7;       // lower = slower wave movement (try 0.5 too)

for (let i = 0; i < position.count; i++) {
  const x = position.getX(i);
  const y = position.getZ(i);

  // Slow but dramatic wave
  const waveHeight = amplitude * Math.sin(x * frequency + time * speed) * Math.cos(y * frequency + time * speed);

  position.setY(i, waveHeight);

  const normalizedHeight = (waveHeight / amplitude + 1) / 2;

  const r = normalizedHeight;
  const g = 0;
  const b = 1 - normalizedHeight;

  color.setXYZ(i, r, g, b);
}



  position.needsUpdate = true;
  color.needsUpdate = true;

  renderer.render(scene, camera);
}

animate();

// 8. Handle resize
window.addEventListener('resize', () => {
  camera.aspect = window.innerWidth / window.innerHeight;
  camera.updateProjectionMatrix();

  renderer.setSize(window.innerWidth, window.innerHeight);
});
